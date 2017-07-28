package kth.com.unithon11team.api.MusicalService.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kinamare on 2017-07-29.
 */

public class Row {
	@SerializedName("CONTENTS_CODE")
	@Expose
	public String mContentsCode;
	@SerializedName("ESTC_GENR_CODE")
	@Expose
	public String mEstcGenrCode;
	@SerializedName("ESTC_GENR_NM")
	@Expose
	public String mEstcGenrNm;
	@SerializedName("SHOW_SDATE")
	@Expose
	public String mShowSDate;
	@SerializedName("SHOW_EDATE")
	@Expose
	public String mShowEDate;
	@SerializedName("CINE_CODE")
	@Expose
	public String mCineCode;
	@SerializedName("CINE_NAME")
	@Expose
	public String mCineName;
	@SerializedName("SHOW_TIME")
	@Expose
	public String mShowTime;
	@SerializedName("RUNTIME")
	@Expose
	public String mRuntime;
	@SerializedName("DIRECTOR")
	@Expose
	public String mDirector;
	@SerializedName("SUPPORT")
	@Expose
	public String mSupport;
	@SerializedName("SPONSOR")
	@Expose
	public String mSponsor;
	@SerializedName("REFERCON")
	@Expose
	public String mRefercon;
	@SerializedName("MAINFILENAME")
	@Expose
	public String mMainFileName;
	@SerializedName("OPTION1")
	@Expose
	public String mOption1;
	@SerializedName("TITLE_CODE_NAME")
	@Expose
	public String mTitleCodeName;
	@SerializedName("TK_PRICE")
	@Expose
	public String mTkPrice;
	@SerializedName("DC_INFO")
	@Expose
	public String mDcInfo;
	@SerializedName("PWRITER")
	@Expose
	public String mPWrite;
	@SerializedName("STAFF")
	@Expose
	public String mStaff;
	@SerializedName("RE_AGE")
	@Expose
	public String mReAge;
	@SerializedName("RE_SEX")
	@Expose
	public String mReSex;
	@SerializedName("INPUT_DATE")
	@Expose
	public String mInputDate;
	@SerializedName("EDIT_DATE")
	@Expose
	public String mEditDate;

}
