package kth.com.unithon11team.api;


import com.vocketlist.android.network.service.ErrorChecker;

import kth.com.unithon11team.api.basemodel.BaseResponse;

/**
 * Created by SeungTaek.Lim(Naver corp) & hyunsung.sin(Inu.stu)
 */
public class BaseServiceErrorChecker<T> implements ErrorChecker<BaseResponse<T>> {
    @Override
    public void checkError(BaseResponse<T> data) throws RuntimeException {
        if (data == null) {
            throw new BaseServiceException();
        }

        if (data.mSuccess == false ) {
            throw new BaseServiceException(data);
        }
    }
}
