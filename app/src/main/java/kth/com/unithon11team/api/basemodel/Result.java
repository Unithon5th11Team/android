package kth.com.unithon11team.api.basemodel;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import kth.com.unithon11team.api.MusicalService.Model.Musical;

import kth.com.unithon11team.api.MusicalService.Model.MusicalDetail;
import kth.com.unithon11team.api.rekognation.model.RecognationImage;

/**
 * Created by kinamare on 2017-07-20.
 * 템플릿 클래스를 명시적으로 사용
 */

public class Result {
	@Nullable
	@SerializedName("emotion_data") public List<RecognationImage> recognationImageList;

	@SerializedName("musical") public List<Musical> musicalList;

	@SerializedName("all_musicals") public List<Musical> allMusicalList;

	@SerializedName("musical_detail") public MusicalDetail musicalDetail;
}
