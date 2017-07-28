package kth.com.unithon11team.api.basemodel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author kinamare (nanamare.tistory.com)
 * @param <T> 어떤 데이터 형이 들어와도 직렬화 가능
 */
public class BaseResponse<T> implements Serializable {
	@SerializedName("result") public T mResult;
	@SerializedName("success") public boolean mSuccess;
	@SerializedName("message") public String mMessage;
}