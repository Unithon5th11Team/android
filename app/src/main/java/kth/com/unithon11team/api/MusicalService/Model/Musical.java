package kth.com.unithon11team.api.MusicalService.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kinamare on 2017-07-29.
 */

public class Musical {


	@SerializedName("performanceNo")
	@Expose
	public int mPerformanceNo;
	@SerializedName("title")
	@Expose
	public String mTitle;
	@SerializedName("genre")
	@Expose
	public String mGenre;
	@SerializedName("date")
	@Expose
	public String mDate;
	@SerializedName("loc")
	@Expose
	public String mLoc;
	@SerializedName("members")
	@Expose
	public String mMembers;
	@SerializedName("grade")
	@Expose
	public String mGrade;
	@SerializedName("time")
	@Expose
	public String mTime;
	@SerializedName("imageURL")
	@Expose
	public String mImageURL;
	@SerializedName("siteURL")
	@Expose
	public String mSiteURL;
	@SerializedName("ticketingURL")
	@Expose
	public String mTicketingURL;
	@SerializedName("contents1")
	@Expose
	public String mContents1;
	@SerializedName("contents2")
	@Expose
	public String mContents2;

}
