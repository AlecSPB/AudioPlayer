package com.tc.audioplayer.utils.shell;

/**
 * Created by itcayman on 2017/9/27.
 */

public class CommandResult {
    /**
     * result of command
     **/
    public int result;
    /**
     * success message of command result
     **/
    public String successMsg;
    /**
     * error message of command result
     **/
    public String errorMsg;


    public CommandResult(int result) {
        this.result = result;
    }


    public CommandResult(int result, String successMsg, String errorMsg) {
        this.result = result;
        this.successMsg = successMsg;
        this.errorMsg = errorMsg;
    }
}
