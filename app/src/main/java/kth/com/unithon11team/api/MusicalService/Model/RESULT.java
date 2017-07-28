package kth.com.unithon11team.api.MusicalService.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kinamare on 2017-07-29.
 */

class RESULT {
	@SerializedName("CODE")
	@Expose
	public String mCode;
	@SerializedName("MESSAGE")
	@Expose
	public String mMessage;
}
