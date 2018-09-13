package com.business.gateway.config;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

/**
 * @describe 权限配置
 * @author wupeng
 * @createtime 2017年9月18日
 */
@Component
public class PermissionConfig {

	
	/**
	 * 需要登录访问的资源
	 */
	private List<String> needSessionResourceList(){
		List<String> urls = Lists.newArrayList();
		urls.add("^/roadoor-app-biz/account/queryAccountInfo");	
		urls.add("^/roadoor-app-biz/account/queryTradeList");	
		urls.add("^/roadoor-app-biz/deposit/topUp/pre");	
		urls.add("^/roadoor-app-biz/deposit/topUp/recharge");	
		urls.add("^/roadoor-app-biz/deposit/topUp/queryResult");	
		urls.add("^/roadoor-app-biz/deposit/topUp/queryPage");	
		urls.add("^/roadoor-app-biz/deposit/topUp/queryDetail");	
		urls.add("^/roadoor-app-biz/deposit/topUp/getTopupRecord");	
		urls.add("^/roadoor-app-biz/deposit/withdraw/pre");	
		urls.add("^/roadoor-app-biz/deposit/withdraw/apply");	
		urls.add("^/roadoor-app-biz/deposit/withdraw/calFee");	
		urls.add("^/roadoor-app-biz/deposit/withdraw/queryAppPage");	
		urls.add("^/roadoor-app-biz/deposit/withdraw/queryAppDetail");	
		urls.add("^/roadoor-app-biz/deposit/withdraw/queryResult");	
		urls.add("^/roadoor-app-biz/user/queryUserAccountStatus");	
		urls.add("^/roadoor-app-biz/pinan/authorize");	
		urls.add("^/roadoor-app-biz/pinan/updatePhone");	
		urls.add("^/roadoor-app-biz/flow/queryMyselfInfo");	
		urls.add("^/roadoor-app-biz/user/inviteCode");	
		urls.add("^/roadoor-app-biz/user/depoist/openAcc");	
		urls.add("^/roadoor-app-biz/user/cardbind/queryDepoistOpenAccState");	
		urls.add("^/roadoor-app-biz/user/depoist/findOpenAccMsgId");	
		urls.add("^/roadoor-app-biz/user/app/logout");	
		urls.add("^/roadoor-app-biz/queryUserBid/queryAssetBIdList");	
		urls.add("^/roadoor-app-biz/queryUserBid/findAssetBidInfoViwe");	
		urls.add("^/roadoor-app-biz/bidDept/queryInvestAssets");	
		urls.add("^/roadoor-app-biz/bidDept/queryBidDebtApplyInfo");	
		urls.add("^/roadoor-app-biz/account/scatterTransferPre");	
		urls.add("^/roadoor-app-biz/account/scatterTransfer");	
		urls.add("^/roadoor-app-biz/preplan/homePage/queryRecommendProduct");	
		urls.add("^/roadoor-app-biz/user/app/changePassword");	
		urls.add("^/roadoor-app-biz/userDepositController/userPasswordSet");	
		urls.add("^/roadoor-app-biz/userDepositController/userPasswordUpdate");	
		urls.add("^/roadoor-app-biz/userDepositController/userPasswordReset");	
		urls.add("^/roadoor-app-biz/user/depoist/bindCard");	
		urls.add("^/roadoor-app-biz/user/depoist/unBindCard");	
		urls.add("^/roadoor-app-biz/user/depoist/checkBeforeUnbind");	
		urls.add("^/roadoor-app-biz/user/depoist/insertUserCheckInfo");	
		urls.add("^/roadoor-app-biz/scyd/applyExit");	
		urls.add("^/roadoor-app-biz/mixture/participateNow");	
		urls.add("^/roadoor-app-biz/mixture/queryParticipateNowInfo");	
		urls.add("^/roadoor-app-biz/preplan/query/propertyQuery");	
		urls.add("^/roadoor-app-biz/preplan/query/getPartyProductionInfo");	
		urls.add("^/roadoor-app-biz/preplan/query/getPartyProductionDetailInfo");	
		urls.add("^/roadoor-app-biz/preplan/query/queryBidOrderByPpcId");	
		urls.add("^/roadoor-app-biz/renewContract/updateRenewContractStatus");	
		urls.add("^/roadoor-app-biz/renewContract/queryRenewContractPlan");	
		urls.add("^/roadoor-app-biz/renewContract/queryRenewContractRecords");	
		urls.add("^/roadoor-app-biz/mixture/queryParticipateNowInfo");	
		urls.add("^/roadoor-app-biz/scyd/exitModifyStatus");	
		urls.add("^/roadoor-app-biz/activity/redPackage/queryByUidFront");	
		urls.add("^/roadoor-app-biz/preplan/query/queryIsInvestXsPreplan");	
		urls.add("^/roadoor-app-biz/risk/queryQusetions");	
		urls.add("^/roadoor-app-biz/risk/commitSheet");	
		urls.add("^/roadoor-app-biz/risk/queryUserResult");	
		urls.add("^/roadoor-app-biz/coupon/exchangeCoupon");	
		urls.add("^/roadoor-app-biz/activity/redPackage/queryNewAppRedPackageInfo");	
		urls.add("^/roadoor-app-biz/activity/redPackage/usePacketByUid");	
		urls.add("^/roadoor-app-biz/feedBack/addFeedBackInfo");	
		urls.add("^/roadoor-app-biz/mgmQueryRecord/queryInviteProfile");	
		urls.add("^/roadoor-app-biz/mgmQueryRecord/queryInviteRecordInfo");	
		urls.add("^/roadoor-app-biz/mgmQueryRecord/queryInviteRecordDetail");	
		urls.add("^/roadoor-app-biz/mgm/reward/findUserReward");	
		urls.add("^/roadoor-app-biz/flow/queryFlowAndRedInfo");	
		urls.add("^/roadoor-app-biz/flow/queryFlowRunningList");	
		urls.add("^/roadoor-app-biz/flow/giftFlowToFriend");	
		urls.add("^/roadoor-app-biz/flow/extractFlowToPhone");	
		urls.add("^/roadoor-app-biz/flow/rechargeFlow");	
		urls.add("^/roadoor-app-biz/blogroll/saveBlogRoll");	
		urls.add("^/roadoor-app-biz/blogroll/updateBlogRoll");	
		urls.add("^/roadoor-app-biz/blogroll/deleteBlogRoll");	
		urls.add("^/roadoor-app-biz/tkd/saveTKD");	
		urls.add("^/roadoor-app-biz/tkd/updateTKD");	
		urls.add("^/roadoor-user-biz/newsCategory/insert");
		urls.add("^/roadoor-user-biz/newsCategory/update");
		urls.add("^/roadoor-user-biz/newsCategory/delByNcId");
		urls.add("^/roadoor-user-biz/newsCategory/queryList");
		urls.add("^/roadoor-user-biz/newsCategory/queryPage");
		urls.add("^/roadoor-user-biz/keyWords/insertByParam");
		urls.add("^/roadoor-user-biz/keyWords/updateByParam");
		urls.add("^/roadoor-user-biz/keyWords/selectListByParam");
		urls.add("^/roadoor-user-biz/keyWords/selectByPrimaryKey");
		urls.add("^/roadoor-user-biz/keyWords/applyAllArticles");
		urls.add("^/roadoor-user-biz/newsArticle/insert");
		urls.add("^/roadoor-user-biz/newsArticle/update");
		urls.add("^/roadoor-user-biz/newsArticle/updateReleaseStatus");
		urls.add("^/roadoor-user-biz/newsArticle/delStatus");
		urls.add("^/roadoor-user-biz/newsArticle/updateTopStatus");
		urls.add("^/roadoor-user-biz/newsArticle/updateClickQuantity");
		urls.add("^/roadoor-user-biz/newsArticle/selectListPage");
		//add more ...
		return urls;
	}
	
	/**
	 * 需要token校验防重复提交访问的资源
	 */
	private List<String> needTokenResourceList(){
		List<String> urls = Lists.newArrayList();
		urls.add("^/roadoor-app-biz/deposit/topUp/pre");
		urls.add("^/roadoor-app-biz/deposit/topUp/recharge");
		urls.add("^/roadoor-app-biz/deposit/withdraw/pre");
		urls.add("^/roadoor-app-biz/deposit/withdraw/apply");
		urls.add("^/roadoor-app-biz/pinan/authorize");
		urls.add("^/roadoor-app-biz/pinan/updatePhone");
		urls.add("^/roadoor-app-biz/user/depoist/openAcc");
		urls.add("^/roadoor-app-biz/account/scatterTransferPre");
		urls.add("^/roadoor-app-biz/account/scatterTransfer");
		urls.add("^/roadoor-app-biz/userDepositController/userPasswordSet");
		urls.add("^/roadoor-app-biz/userDepositController/userPasswordUpdate");
		urls.add("^/roadoor-app-biz/userDepositController/userPasswordReset");
		urls.add("^/roadoor-app-biz/user/depoist/bindCard");
		urls.add("^/roadoor-app-biz/mixture/participateNow");
		urls.add("^/roadoor-app-biz/coupon/exchangeCoupon");
		urls.add("^/roadoor-app-biz/activity/redPackage/usePacketByUid");
		//add more ...
		return urls;
	}
	
	
	 /**
		 * 判断当前资源是否需要登录
		 */
		public boolean isSessionRequire(String curUrl) {
			List<String> urls = needSessionResourceList();
			for (String pattern : urls) {
				if(Pattern.matches(pattern, curUrl)){
					return true;
				}
			}
			return false;
		}
		
	    /**
		 * 判断当前资源是否需要token防重
		 */
		public boolean isTokenRequire(String curUrl) {
			List<String> urls = needTokenResourceList();
			for (String pattern : urls) {
				if(Pattern.matches(pattern, curUrl)){
					return true;
				}
			}
			return false;
		}
}
