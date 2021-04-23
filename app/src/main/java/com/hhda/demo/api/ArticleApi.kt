package com.hhda.demo.api

import com.hhda.demo.dto.ArticleDTO
import com.hhda.demo.dto.BaseResult
import com.hhda.demo.dto.PageResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ArticleApi {


    @GET("/article/list/{pageNo}/json")
    fun getHomePageArticle(@Path("pageNo") page: Int): Observable<BaseResult<PageResult<ArticleDTO>>>
}