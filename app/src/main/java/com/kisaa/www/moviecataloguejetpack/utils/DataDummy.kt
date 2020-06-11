package com.kisaa.www.moviecataloguejetpack.utils

import com.kisaa.www.moviecataloguejetpack.data.source.local.FavoriteEntity
import com.kisaa.www.moviecataloguejetpack.data.source.remote.MovieEntity
import com.kisaa.www.moviecataloguejetpack.data.source.remote.TvShowEntity

object DataDummy {
    fun getListMovies(): List<MovieEntity> {
        return listOf(
            MovieEntity(
                "419704",
                "/xBHvZcjRiWyobQ9kxBhO6B2dtRI.jpg",
                "Ad Astra",
                "The near future, a time when both hope and hardships drive humanity to look to the stars and beyond. While a mysterious phenomenon menaces to destroy life on planet Earth, astronaut Roy McBride undertakes a mission across the immensity of space and its many perils to uncover the truth about a lost expedition that decades before boldly faced emptiness and silence in search of the unknown.",
                "5.9",
                "/5BwqwxMEjeFtdknRV792Svo0K1v.jpg"
            ),
            MovieEntity(
                "338762",
                "/8WUVHemHFH2ZIP6NWkwlHWsyrEL.jpg",
                "Bloodshot",
                "After he and his wife are murdered, marine Ray Garrison is resurrected by a team of scientists. Enhanced with nanotechnology, he becomes a superhuman, biotech killing machine—'Bloodshot'. As Ray first trains with fellow super-soldiers, he cannot recall anything from his former life. But when his memories flood back and he remembers the man that killed both him and his wife, he breaks out of the facility to get revenge, only to discover that there's more to the conspiracy than he thought.",
                "6.4",
                "/ocUrMYbdjknu2TwzMHKT9PBBQRw.jpg"
            ),
            MovieEntity(
                "570670",
                "/4U7hpTK0XTQBKT5X60bKmJd05ha.jpg",
                "The Invisible Man",
                "When Cecilia's abusive ex takes his own life and leaves her his fortune, she suspects his death was a hoax. As a series of coincidences turn lethal, Cecilia works to prove that she is being hunted by someone nobody can see.",
                "7.1",
                "/uZMZyvarQuXLRqf3xdpdMqzdtjb.jpg"
            )
        )
    }

    fun getListTvShows(): List<TvShowEntity> {
        return listOf(
            TvShowEntity(
                "93533",
                "/jQNOzoiaIQWxJAx8OUighnvnhRA.jpg",
                "Thieves of the Wood",
                "Charismatic highwayman Jan de Lichte leads the oppressed and downtrodden in a revolt against the corrupt aristocracy of 18th-century Belgium.",
                "5.9",
                "/9KimY8zgzv36HFtsHFsQMhOyH9f.jpg"
            ),
            TvShowEntity(
                "63247",
                "/y55oBgf6bVMI7sFNXwJDrSIxPQt.jpg",
                "Westworld",
                "A dark odyssey about the dawn of artificial consciousness and the evolution of sin. Set at the intersection of the near future and the reimagined past, it explores a world in which every human appetite, no matter how noble or depraved, can be indulged.",
                "8.1",
                "/yGNnjoIGOdQy3douq60tULY8teK.jpg"
            ),
            TvShowEntity(
                "1402",
                "/5l10EjdgPxu8Gbl5Ww6SWkVQH6T.jpg",
                "The Walking Dead",
                "Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.",
                "7.4",
                "/wXXaPMgrv96NkH8KD1TMdS2d7iq.jpg"
            )
        )
    }

    fun getMovie(id: String?): MovieEntity {
        if (id.equals("419704")) {
            return MovieEntity(
                "419704",
                "/xBHvZcjRiWyobQ9kxBhO6B2dtRI.jpg",
                "Ad Astra",
                "The near future, a time when both hope and hardships drive humanity to look to the stars and beyond. While a mysterious phenomenon menaces to destroy life on planet Earth, astronaut Roy McBride undertakes a mission across the immensity of space and its many perils to uncover the truth about a lost expedition that decades before boldly faced emptiness and silence in search of the unknown.",
                "5.9",
                "/5BwqwxMEjeFtdknRV792Svo0K1v.jpg"
            )
        }
        return MovieEntity()
    }

    fun getTvShow(id: String?): TvShowEntity {
        if (id.equals("1402")) {
            return TvShowEntity(
                "1402",
                "/5l10EjdgPxu8Gbl5Ww6SWkVQH6T.jpg",
                "The Walking Dead",
                "Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.",
                "7.4",
                "/wXXaPMgrv96NkH8KD1TMdS2d7iq.jpg"
            )
        }
        return TvShowEntity()
    }

    fun getFavoriteMovie(id: String): FavoriteEntity? {
        if (id == "419704") {
            return FavoriteEntity(
                "419704",
                "Ad Astra",
                "The near future, a time when both hope and hardships drive humanity to look to the stars and beyond. While a mysterious phenomenon menaces to destroy life on planet Earth, astronaut Roy McBride undertakes a mission across the immensity of space and its many perils to uncover the truth about a lost expedition that decades before boldly faced emptiness and silence in search of the unknown.",
                "5.9",
                "/xBHvZcjRiWyobQ9kxBhO6B2dtRI.jpg",
                "movie"
            )
        }
        return null
    }

    fun getFavoriteTvShow(id: String): FavoriteEntity? {
        if (id == "1402") {
            return FavoriteEntity(
                "1402",
                "The Walking Dead",
                "Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.",
                "7.4",
                "/5l10EjdgPxu8Gbl5Ww6SWkVQH6T.jpg",
                "tvShow"
            )
        }
        return null
    }

    fun getListFavoriteMovies(): List<FavoriteEntity> {
        return listOf(
            FavoriteEntity(
                "419704",
                "Ad Astra",
                "The near future, a time when both hope and hardships drive humanity to look to the stars and beyond. While a mysterious phenomenon menaces to destroy life on planet Earth, astronaut Roy McBride undertakes a mission across the immensity of space and its many perils to uncover the truth about a lost expedition that decades before boldly faced emptiness and silence in search of the unknown.",
                "5.9",
                "/xBHvZcjRiWyobQ9kxBhO6B2dtRI.jpg",
                "movie"
            ),
            FavoriteEntity(
                "338762",
                "Bloodshot",
                "After he and his wife are murdered, marine Ray Garrison is resurrected by a team of scientists. Enhanced with nanotechnology, he becomes a superhuman, biotech killing machine—'Bloodshot'. As Ray first trains with fellow super-soldiers, he cannot recall anything from his former life. But when his memories flood back and he remembers the man that killed both him and his wife, he breaks out of the facility to get revenge, only to discover that there's more to the conspiracy than he thought.",
                "6.4",
                "/8WUVHemHFH2ZIP6NWkwlHWsyrEL.jpg",
                "movie"
            ),
            FavoriteEntity(
                "570670",
                "The Invisible Man",
                "When Cecilia's abusive ex takes his own life and leaves her his fortune, she suspects his death was a hoax. As a series of coincidences turn lethal, Cecilia works to prove that she is being hunted by someone nobody can see.",
                "7.1",
                "/4U7hpTK0XTQBKT5X60bKmJd05ha.jpg",
                "movie"
            )
        )
    }

    fun getListFavoriteTvShows(): List<FavoriteEntity> {
        return listOf(
            FavoriteEntity(
                "93533",
                "Thieves of the Wood",
                "Charismatic highwayman Jan de Lichte leads the oppressed and downtrodden in a revolt against the corrupt aristocracy of 18th-century Belgium.",
                "5.9",
                "/jQNOzoiaIQWxJAx8OUighnvnhRA.jpg",
                "tvShow"
            ),
            FavoriteEntity(
                "63247",
                "Westworld",
                "A dark odyssey about the dawn of artificial consciousness and the evolution of sin. Set at the intersection of the near future and the reimagined past, it explores a world in which every human appetite, no matter how noble or depraved, can be indulged.",
                "8.1",
                "/y55oBgf6bVMI7sFNXwJDrSIxPQt.jpg",
                "tvShow"
            ),
            FavoriteEntity(
                "1402",
                "The Walking Dead",
                "Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.",
                "7.4",
                "/5l10EjdgPxu8Gbl5Ww6SWkVQH6T.jpg",
                "tvShow"
            )
        )
    }
}