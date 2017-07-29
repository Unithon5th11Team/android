package kth.com.unithon11team.api.rekognation;

import android.database.Observable;

import com.vocketlist.android.network.executor.Priority;
import com.vocketlist.android.network.service.ServiceErrorChecker;
import com.vocketlist.android.network.service.ServiceHelper;

import java.io.File;

import kth.com.unithon11team.api.BaseServiceErrorChecker;
import kth.com.unithon11team.api.ServiceDefine;
import kth.com.unithon11team.api.basemodel.BaseResponse;
import kth.com.unithon11team.api.basemodel.Result;
import kth.com.unithon11team.api.rekognation.model.RecognationImage;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;

/**
 * Created by kinamare on 2017-07-29.
 */

public class RekognationServiceManager {


	private static final RekognationService service = ServiceDefine.retrofit.create(RekognationService.class);

	private RekognationServiceManager() {

	}

	public static rx.Observable sendToImage(String imagePath){

		MultipartBody.Part image = null;

		if (imagePath != null) {
			image = getMultipartBody(imagePath);
		}

		return service.sendToImage(image)
				.subscribeOn(ServiceHelper.getPriorityScheduler(Priority.MEDIUM))
				.lift(new ServiceErrorChecker<>(new BaseServiceErrorChecker<RecognationImage>()));

	}

	private static MultipartBody.Part getMultipartBody(String imagePath) {
		if (imagePath == null) {
			return null;
		}

		File file = new File(imagePath);

		RequestBody requestFile = RequestBody.create(MediaType.parse("mImage/*"), file);

		// MultipartBody.Part is used to send also the actual file name
		MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestFile);

		return body;
	}



}
