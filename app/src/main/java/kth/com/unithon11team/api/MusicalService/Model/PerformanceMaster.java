package kth.com.unithon11team.api.MusicalService.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kinamare on 2017-07-29.
 */

class PerformanceMaster {
	@SerializedName("list_total_count")
	@Expose
	public int mListTotalCount;
	@SerializedName("RESULT")
	@Expose
	public RESULT mResult;
	@SerializedName("row")
	@Expose
	public List<Row> mRowList = null;
}
