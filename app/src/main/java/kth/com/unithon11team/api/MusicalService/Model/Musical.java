package kth.com.unithon11team.api.MusicalService.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kinamare on 2017-07-29.
 */

public class Musical {

	@SerializedName("PerformanceMaster")
	@Expose
	public PerformanceMaster mPerformanceMaster;
}
