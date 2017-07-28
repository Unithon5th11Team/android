package kth.com.unithon11team.api;


import kth.com.unithon11team.api.basemodel.BaseResponse;

/**
 * Created by SeungTaek.Lim(Naver corp) hyunsung.sin(Inu.stu)
 */
public class BaseServiceException extends RuntimeException {
    private BaseResponse<?> mData;

    public BaseServiceException() {
        super();
    }

    public BaseServiceException(BaseResponse<?> data) {
        super();
        mData = data;
    }

    public <T> T getData() {
        return (T) mData;
    }

    @Override
    public String getMessage() {
        if (mData != null
                && mData.mMessage != null) {
            return mData.mMessage;
        }

        return super.getMessage();
    }
}
