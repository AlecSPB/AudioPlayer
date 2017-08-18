package com.tc.model.net;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.tc.model.exception.ServerInnerErrorException;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by tianchao on 2017/8/3.
 */

public class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final Type type;

    GsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String reader = value.string();
        try {
            JsonParser parser = new JsonParser();
            JsonElement rootElement = parser.parse(reader);
            if (!rootElement.isJsonObject() && !rootElement.isJsonArray()) {
                throw new JsonParseException("Root is not JsonObject or JsonArray");
            }
            if (rootElement.isJsonObject()) {
                JsonObject root = rootElement.getAsJsonObject();
                //如果有error，必然会抛出异常
                if (root.has("error")) {
                    JsonElement error = root.get("error");
                    convertErrorElement(error);
                }
            }
            return convertDataElement(rootElement, type);
        } finally {
        }
    }

    /**
     * 解析数据
     *
     * @param root
     * @param type
     * @return
     */
    private T convertDataElement(JsonElement root, Type type) {
        ParseNodePath parseNodePath = ParseNodePathHelper.getParseNodePath(type);
        if (parseNodePath == null) {
            return covertDataDefault(root, type);
        }

        List<List<String>> paths = ParseNodePathHelper.getParsePathList(parseNodePath);
        if (paths == null || paths.size() == 0) {
            return covertDataDefault(root, type);
        }
        if (type instanceof ParameterizedType)
            return convertArrayDataByNodePath(root, type, paths);
        return covertDataByNodePath(root, type, paths);
    }

    private T covertDataDefault(JsonElement root, Type type) {
        return gson.fromJson(root, type);
    }

    /**
     * 根据多个path路径解析数组数据，若解析失败则抛出异常
     *
     * @param root  the root of JsonElement
     * @param type  new TypeToken&lt;Collection&lt;Foo&gt;&gt;(){}.getType();
     * @param paths the list of path to be resolved
     */
    private T convertArrayDataByNodePath(JsonElement root, Type type, List<List<String>> paths)
            throws ParseNodePathHelper.IllegalParseNodePathException {
        JsonElement currentNode = root;
        for (List<String> path : paths) {
            for (String node : path) {
                if (currentNode.isJsonArray()) {
                    return gson.fromJson(currentNode, type);
                } else if (currentNode.isJsonObject() && currentNode.getAsJsonObject().has(node)) {
                    currentNode = currentNode.getAsJsonObject().get(node);
                }
            }
        }
        return gson.fromJson(currentNode, type);
    }

    /**
     * 根据多个path路径解析，若解析失败则抛出异常
     *
     * @param root  the root of JsonElement
     * @param type  new TypeToken&lt;Collection&lt;Foo&gt;&gt;(){}.getType();
     * @param paths the list of path to be resolved
     */
    private T covertDataByNodePath(JsonElement root, Type type, List<List<String>> paths)
            throws ParseNodePathHelper.IllegalParseNodePathException {
        JsonElement currentNode = root;
        boolean parseSuccess = false;

        for (List<String> path : paths) {
            for (String node : path) {
                if (!currentNode.isJsonObject() || !currentNode.getAsJsonObject().has(node)) {
                    parseSuccess = false;
                    break;
                }
                currentNode = currentNode.getAsJsonObject().get(node);
                parseSuccess = true;
            }
            if (parseSuccess)
                break;
        }
        return gson.fromJson(currentNode, type);
    }


    /**
     * 抛出与后台协商异常
     *
     * @param error
     * @throws IOException
     */
    private void convertErrorElement(JsonElement error) throws IOException {
        JsonObject errorObject = error.getAsJsonObject();
        int code = errorObject.has("code") ? errorObject.get(
                "code").getAsInt() : 400;
        String message = errorObject.has("message") ? errorObject
                .get("message").getAsString() : "";
        String info = errorObject.has("info") ? errorObject.get("info").getAsString() : "";
        throw new ServerInnerErrorException(code, message, info);
    }
}
