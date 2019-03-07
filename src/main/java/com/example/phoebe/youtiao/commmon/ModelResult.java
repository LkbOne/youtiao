package com.example.phoebe.youtiao.commmon;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import java.io.Serializable;

@Data
public class ModelResult<T> implements Serializable {

    private int errCode;
	private String errMsg;
	private T data;

	public ModelResult() {
	}

    public ModelResult(SHErrorCode code) {
    	this.errCode = code.getErrorCode();
    	this.errMsg = code.getErrorMessage();
    }

    public ModelResult(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public ModelResult(int errCode, String errMsg, T data) {
    	this.errCode = errCode;
        this.errMsg = errMsg;
        this.data = data;
    }
    
    public ModelResult(SHErrorCode code, T data) {
    	this.errCode = code.getErrorCode();
    	this.errMsg = code.getErrorMessage();
    	this.data = data;
    }
	@JsonIgnore
	public boolean isSuccess() {
		return errCode == 0;
	}
	public static <E> ModelResult<E> newSuccess(){
		return new ModelResult<>(SHErrorCode.SUCCESS);
	}

	public static <E> ModelResult<E> newSuccess(E data){
		return new ModelResult<>(SHErrorCode.SUCCESS, data);
	}

	public static <E> ModelResult<E> newError(SHErrorCode code){return new ModelResult<>(code); }

	public static <E> ModelResult<E> newError(int errCode, String errMsg){return new ModelResult<>(errCode, errMsg); }
}
