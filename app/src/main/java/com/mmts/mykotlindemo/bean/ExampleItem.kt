package com.mmts.mykotlindemo.bean

/**
 * @author: As
 * @date: 2022-02-28
 */

data class ExampleItem(val frontmatter: Frontmatter? = null)

data class Frontmatter(
    val banner: Banner? = null,
    val categories: List<String>,
    val date: String? = null,
    val draft: Any,
    val language: String? = null,
    val path: String? = null,
    val tags: List<String>,
    val title: String? = "input title"
)

data class Banner(
    val childImageSharp: ChildImageSharp? = null
)

data class ChildImageSharp(
    val fixed: Fixed? = null
)

data class Fixed(
    val src: String? = null
)