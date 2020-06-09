package com.wu.mj.module.home.frame.model

import java.io.Serializable

/**
 *
 * 作者:吴奎庆
 *
 * 时间:2020/6/8
 *
 * 用途: 章节的bean
 */


class QuestionInfo : Serializable {
    var id: String? = null
    var title: String? = null
    var index: String? = null
    var type: String? = null
    //正确答案
    var right_answer: String? = null

    //知识点
    var knowledge_point: String? = null
    //扩展
    var explain: String? = null
    var exam_id: String? = null
    //我的答案
    var my_answer: String? = null
}