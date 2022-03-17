import requests from "./ajax";

export const reqCarousel = () => requests.get(`/carousel`);

export const reqDailyRecommend = () => requests.get(`/branch/daily_recommend`);

export const reqWeekSchedule = () => requests.get(`/branch/week_schedule`);

export const reqRecentUpdate = () => requests.get(`/branch/recent_update`);

export const reqBranchByBranchId = (branchId) => requests({url:`/branch/${branchId}`,method: "GET"});

export const reqCollectionByBranchId = (branchId) => requests({url:`/collection/branchId/${branchId}`,method: "GET"});

export const reqInsertCollection = (branchId) => requests({url:`/collection`,params: {branchId},method: "POST"});

export const reqDeleteCollection = (branchId) => requests({url:`/collection`,params: {branchId},method: "DELETE"});

export const reqCommentByBranchId = (branchId,pageNum) => requests({url:`/comment/branchId/${branchId}/${pageNum}`,method: "GET"});

export const reqCommentByParent = (parent,pageNum) => requests({url:`/comment/parent/${parent}/${pageNum}`,method: "GET"});

export const reqInsertComment = (data) => requests({url:`/comment`,data,method: "POST"});

export const reqSimilarRecommendByBranchId = (branchId) => requests({url: `/branch/${branchId}/similar_recommend`,method: "GET"});

export const reqRelateByBranchId = (branchId) => requests({url: `/branch/${branchId}/relate`,method: "GET"});

export const reqEpisodesByBranchId = (branchId) => requests({url: `/episode/${branchId}`,method: "GET"});

export const reqEpisodeByBranchIdAndEpisodeNumber = (branchId,episodeNumber) => requests({url:`/episode/${branchId}/${episodeNumber}`,method:"GET"});

export const reqIndexTable = () => requests.get(`/category`);

export const reqCategorySearchResult = (query) => requests({url:`/search/categories`,method: "GET",params:query});

export const reqSearchResultByKeyword = (query) => requests({url:`/search/keyword`,method: "GET", params:query});

export const login = (fromData) => requests({url: `/auth/login`,method: "POST", data: fromData});

export const reqAccountInfo = () => requests({url: `/account`,method: "GET"});

export const reqVerificationCode = (email) => requests({url:`/account/code/${email}`,method: "POST"});

export const reqVerifyCode = (fromData) => requests({url:`/account/verify`,method: "POST",data: fromData});

export const reqRegisterAccount = (data) => requests({url:`/account`,method: "POST",data});

export const reqUpdateNickname = (nickname) => requests({url:`/account/nickname/${nickname}`,method: "PUT"});

export const reqModifyPassword = (formData) => requests({url:`/account/password`,data: formData,method: "PUT"});

// export const reqCollectionByUsername = (pageNum) => requests({url:`/collection`,params: {pageNum},method: "GET"});

export const reqCollectionByUsernameAndKeyword = (keyword,pageNum) => requests({url:`/collection`,params: {keyword,pageNum},method: "GET"})

export const reqDeleteBulkCollection = (formData) => requests({url:`/collection/bulk`,data: formData,method: "DELETE"});

export const reqCommentByUsername = (pageNum) => requests({url:`/comment/username/${pageNum}`,method: "GET"});

export const reqUpdateProfileImage = (formData) => requests({url:`/account/image`,data: formData, method: "PUT"});