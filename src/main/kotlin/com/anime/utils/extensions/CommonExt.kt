package com.anime.utils.extensions

import com.anime.models.Hero
import com.anime.utils.constants.Constants
import com.anime.utils.heroes

fun Int?.calculatePage(): Map<String, Int?> {
    var prePage = this
    var nextPage = this
    when (this) {
        1 -> {
            prePage = null
            nextPage = this.plus(1)
        }
        5 -> {
            prePage = this.minus(1)
            nextPage = null
        }
        in (1 until 5) -> {
            print("im $this")
            nextPage = this?.plus(1)
            prePage = this?.minus(1)
        }
    }
    return mapOf(Constants.PRE_PAGE_KEY to prePage, Constants.NEXT_PAGE_KEY to nextPage)
}


fun String?.searchAnime() : List<Hero> {
    val foundList  = mutableListOf<Hero>()
    return if(!this.isNullOrEmpty()){
        heroes.forEach { (_, hero) ->
            hero.map {
                if(it.name.lowercase().contains(this.lowercase())){
                    foundList.add(it)
                }
            }
        }
        foundList
    }
    else{
        emptyList()
    }

}
