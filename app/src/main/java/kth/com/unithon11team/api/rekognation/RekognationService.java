package kth.com.unithon11team.api.rekognation;



import kth.com.unithon11team.api.basemodel.BaseResponse;
import kth.com.unithon11team.api.rekognation.model.RecognationImage;
import okhttp3.MultipartBody;
import retrofit2.Response;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

/**
 * Created by kinamare on 2017-07-29.
 */

public interface RekognationService {
	@POST("api/rekognation")
	Observable<Response<BaseResponse<RecognationImage>>> sendToImage(@Part MultipartBody.Part image);
}
