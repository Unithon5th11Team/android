package kth.com.unithon11team.api.rekognation.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by kinamare on 2017-07-29.
 */

public class RecognationImage implements Serializable {
	@SerializedName("Type")
	@Expose
	public String type;
	@SerializedName("Confidence")
	@Expose
	public Double confidence;
}
