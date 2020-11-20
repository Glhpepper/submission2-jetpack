package com.example.moviescatalogue.utils

import com.example.moviescatalogue.R
import com.example.moviescatalogue.data.local.entity.DetailEntity
import com.example.moviescatalogue.data.local.entity.DetailTopCastEntity
import com.example.moviescatalogue.data.local.entity.MoviesEntity
import com.example.moviescatalogue.data.local.entity.TvShowsEntity

object DummyData  {

    fun generateDummyMovies(): ArrayList<MoviesEntity> {
        val movies = ArrayList<MoviesEntity>()

        movies.add(
            MoviesEntity(
                "0001",
                "Demon Slayer: Kimetsu no Yaiba",
                "Tanjirō Kamado, joined with Inosuke Hashibira, a boy raised by boars who wears a boar's head," +
                        " and Zenitsu Agatsuma",
                "7.5",
                "${R.drawable.demon_slayer}",
                "Oct 16, 2020"
            )
        )
        movies.add(
            MoviesEntity(
                "0002",
                "Enola Holmes",
                "While searching for her missing mother, intrepid teen Enola Holmes uses her sleuthing skills to outsmart big brother Sherlock and help a runaway lord.",
                "7.6",
                "${R.drawable.enola_holmes}",
                "Sep 23, 2020"
            )
        )
        movies.add(
            MoviesEntity(
                "0003",
                "Love and Monsters",
                "Seven years after the Monsterpocalypse, Joel Dawson, along with the rest of humanity, has been living underground ever since giant creatures took control of the land.",
                "7.6",
                "${R.drawable.love_and_monsters}",
                "Oct 16, 2020"
            )
        )
        movies.add(
            MoviesEntity(
                "0004",
                "Mulan",
                "When the Emperor of China issues a decree that one man per family must serve in the Imperial Chinese Army to defend the country from Huns, Hua Mulan, the eldest daughter of an honored warrior.",
                "7.3",
                "${R.drawable.mulan}",
                "Sep 04, 2020"
            )
        )
        movies.add(
            MoviesEntity(
                "0005",
                "Happy Halloween Scooby-Doo!",
                "Scooby-Doo and the gang team up with their pals, Bill Nye The Science Guy and Elvira Mistress of the Dark, to solve this mystery of gigantic proportions and save Crystal Cove!",
                "8.0",
                "${R.drawable.scoby_doo}",
                "Oct 06, 2020"
            )
        )
        return movies
    }

    fun generateDummyShows(): ArrayList<TvShowsEntity> {
        val shows = ArrayList<TvShowsEntity>()

        shows.add(
            TvShowsEntity(
                "1000",
                "The Walking Dead: World Beyond",
                "A heroic group of teens sheltered from the dangers of the post-apocalyptic " +
                        "world receive a message that inspires them to leave the safety of the only home they " +
                        "have ever known and embark on a cross-country journey to find the one man who can possibly save the world.",
                "7.7",
                "${R.drawable.walking_dead}",
                "Oct 04, 2020"
            )
        )
        shows.add(
            TvShowsEntity(
                "2000",
                "The Haunting of Bly Manor",
                "After an au pair’s tragic death, Henry Wingrave hires a young American nanny to care for his orphaned niece " +
                        "and nephew who reside at Bly Manor with the estate’s chef Owen, groundskeeper Jamie and housekeeper, Mrs. Grose.",
                "7.8",
                "${R.drawable.haunting_bly}",
                "Oct 09, 2020"
            )
        )
        shows.add(
            TvShowsEntity(
                "3000",
                "Emily in Paris",
                "When ambitious Chicago marketing exec Emily unexpectedly lands her dream job in Paris, she embraces a new life as she juggles work, friends and romance.",
                "8.1",
                "${R.drawable.emily_in_paris}",
                "Oct 02, 2020"
            )
        )
        shows.add(
            TvShowsEntity(
                "4000",
                "Tower of God ",
                "There is a tower that summons chosen people called \"Regulars\" with the promise of granting their deepest desires. Whether it be wealth, fame, authority, or something that surpasses them all—everything awaits those who reach the top.",
                "8.7",
                "${R.drawable.tog}",
                "April 2, 2020"
            )
        )
        shows.add(
            TvShowsEntity(
                "5000",
                "Hyouka",
                "Oreki Houtarou is a minimalistic high school boy. One day, he joins the Classic Literature Club at his elder sister's request. There he meets Chitanda Eru, Fukube Satoshi, and Ibara Mayaka.",
                "8.5",
                "${R.drawable.hyouka}",
                "April 23, 2012"
            )
        )
        return shows
    }

    fun generateDetail(id: String): ArrayList<DetailEntity> {
        val detailData = ArrayList<DetailEntity>()

        when (id) {
            "0001" -> detailData.add(
                DetailEntity(
                    "0001",
                    "Demon Slayer",
                    "${R.drawable.demon_slayer}",
                    "Tanjirō Kamado, joined with Inosuke Hashibira, a boy raised by boars who wears a boar's head," +
                            " and Zenitsu Agatsuma, a scared boy who reveals his true power when he sleeps, boards the Infinity Train on a new mission with the Fire Hashira," +
                            " Kyōjurō Rengoku, to defeat a demon who has been tormenting the people and killing the demon slayers who oppose it!",
                    "Masashi Takeuchi - Toshiyuki Shirai - Hideki Hosokawa - Haruo Sotozaki",
                    "9.3",
                    "Almost there...",
                    getTopCastDetail(id)
                )
            )
            "0002" -> detailData.add(
                DetailEntity(
                    "0002",
                    "Enola Holmes",
                    "${R.drawable.enola_holmes}",
                    "While searching for her missing mother, intrepid teen Enola Holmes uses her sleuthing skills to outsmart big brother Sherlock and help a runaway lord.",
                    "Harry Bradbeer",
                    "10",
                    "Yes! Looking good!",
                    getTopCastDetail(id)
                )
            )
            "0003" -> detailData.add(
                DetailEntity(
                    "0003",
                    "Love and Monsters",
                    "${R.drawable.love_and_monsters}",
                    "Seven years after the Monsterpocalypse, Joel Dawson, along with the rest of humanity, has been living underground ever since giant creatures took control of the land." +
                            " After reconnecting over radio with his high school girlfriend Aimee, who is now 80 miles away at a coastal colony, Joel begins to fall for her again. As Joel realizes that there’s nothing left for him underground," +
                            " he decides against all logic to venture out to Aimee, despite all the dangerous monsters that stand in his way.",
                    "Michael Matthews",
                    "10",
                    "Yes! Looking good!",
                    getTopCastDetail(id)
                )
            )
            "0004" -> detailData.add(
                DetailEntity(
                    "0004",
                    "Mulan",
                    "${R.drawable.mulan}",
                    "When the Emperor of China issues a decree that one man per family must serve in the Imperial Chinese Army to defend the country from Huns," +
                            " Hua Mulan, the eldest daughter of an honored warrior, steps in to take the place of her ailing father. She is spirited, determined and quick on her feet." +
                            " Disguised as a man by the name of Hua Jun, she is tested every step of the way and must harness her innermost strength and embrace her true potential.",
                    "Niki Caro",
                    "10",
                    "Yes! Looking good!",
                    getTopCastDetail(id)
                )
            )
            "0005" -> detailData.add(
                DetailEntity(
                    "0005",
                    "Scooby-Doo!",
                    "${R.drawable.scoby_doo}",
                    "Scooby-Doo and the gang team up with their pals, Bill Nye The Science Guy and Elvira Mistress of the Dark, to solve this mystery of gigantic proportions and save Crystal Cove!",
                    "Maxwell Atoms",
                    "10",
                    "Yes! Looking good!",
                    getTopCastDetail(id)
                )
            )
            "1000" -> detailData.add(
                DetailEntity(
                    "1000",
                    "The Walking Dead",
                    "${R.drawable.walking_dead}",
                    "A heroic group of teens sheltered from the dangers of the post-apocalyptic world leave the safety of the only home they have ever known and embark on a cross-country journey to find the one man who can possibly save the world.",
                    "Scott M. Gimple - Matthew Negrete",
                    "10",
                    "Yes! Looking good!",
                    getTopCastDetail(id)
                )
            )
            "2000" -> detailData.add(
                DetailEntity(
                    "2000",
                    "Bly Manor",
                    "${R.drawable.haunting_bly}",
                    "After an au pair’s tragic death, Henry Wingrave hires a young American nanny to care for his orphaned niece and nephew who reside at Bly Manor with the estate’s chef Owen, " +
                            "groundskeeper Jamie and housekeeper, Mrs. Grose. But all is not as it seems at the manor, and centuries of dark secrets of love and loss are waiting to be unearthed in this chilling gothic romance. At Bly Manor, dead doesn’t mean gone.",
                    "Mike Flanagan",
                    "10",
                    "Yes! Looking good!",
                    getTopCastDetail(id)
                )
            )
            "3000" -> detailData.add(
                DetailEntity(
                    "3000",
                    "Emily in Paris",
                    "${R.drawable.emily_in_paris}",
                    "When ambitious Chicago marketing exec Emily unexpectedly lands her dream job in Paris, she embraces a new life as she juggles work, friends and romance.",
                    "Darren Star",
                    "10",
                    "Yes! Looking good!",
                    getTopCastDetail(id)
                )
            )
            "4000" -> detailData.add(
                DetailEntity(
                    "4000",
                    "Tower of God",
                    "${R.drawable.tog}",
                    "There is a tower that summons chosen people called \"Regulars\" with the promise of granting their deepest desires. Whether it be wealth, fame, authority, or something that surpasses them all—everything awaits those who reach the top.\n" +
                            "\n" +
                            "Twenty-Fifth Bam is a boy who had only known a dark cave, a dirty cloth, and an unreachable light his entire life. So when a girl named Rachel came to him through the light, his entire world changed. Becoming close friends with Rachel, he learned various things about the outside world from her. But when Rachel says she must leave him to climb the Tower, his world shatters around him. Vowing to follow after her no matter what it takes, he sets his sight on the tower, and a miracle occurs.\n" +
                            "\n" +
                            "Thus begins the journey of Bam, a young boy who was not chosen by the Tower but opened its gates by himself. They call his kind \"Irregulars\"—beings that have shaken the very foundation of the Tower each time they set foot inside it.",
                    "-",
                    "9.3",
                    "Almost there...",
                    getTopCastDetail(id)
                )
            )
            "5000" -> detailData.add(
                DetailEntity(
                    "5000",
                    "Hyouka",
                    "${R.drawable.hyouka}",
                    "Oreki Houtarou is a minimalistic high school boy. One day, he joins the Classic Literature Club at his elder sister's request. There he meets Chitanda Eru, Fukube Satoshi, and Ibara Mayaka. Chitanda is a calm beautiful girl but she turns into an embodiment of curiosity once she says, \"I'm curious.\" Fukube is a smiling boy with a fantastic memory who calls himself a database. Ibara is a short girl and is strict with others and herself. They begin to investigate a case that occurred 45 years ago." +
                            " Hints of the mystery are buried in an old collection of works of the former members of Classics Club. The collection is titled \"Hyouka.\"",
                    "-",
                    "9.3",
                    "Almost there...",
                    getTopCastDetail(id)
                )
            )
        }

        return detailData
    }

    private fun getTopCastDetail(id: String): ArrayList<DetailTopCastEntity> {
        val detailTopCast = ArrayList<DetailTopCastEntity>()

        when (id) {
            "0001" -> detailTopCast.apply {
                add(
                    DetailTopCastEntity(
                        "${R.drawable.natsuki_hanae}",
                        "Natsuki Hanae",
                        "Tanjirō Kamado"
                    )
                )
                add(
                    DetailTopCastEntity(
                        "${R.drawable.akari_kito}",
                        "Akari Kitō",
                        "Nezuko Kamado"
                    )
                )
                add(
                    DetailTopCastEntity(
                        "${R.drawable.hiro_shimono}",
                        "Hiro Shimono",
                        "Zenitsu Agatsuma"
                    )
                )
                add(
                    DetailTopCastEntity(
                        "${R.drawable.yoshitsugu_matsuoka}",
                        "Yoshitsugu Matsuoka",
                        "Inosuke Hashibira"
                    )
                )
                add(
                    DetailTopCastEntity(
                        "${R.drawable.satoshi_hino}",
                        "Satoshi Hino",
                        "Kyōjurō Rengoku"
                    )
                )
            }
            "0002" -> detailTopCast.apply {
                add(
                    DetailTopCastEntity(
                        "${R.drawable.millie_bobby_brown}",
                        "Millie Bobby Brown",
                        "Enola Holmes"
                    )
                )
                add(
                    DetailTopCastEntity(
                        "${R.drawable.henry_cavill}",
                        "Henry Cavill",
                        "Sherlock Holmes"
                    )
                )
                add(
                    DetailTopCastEntity(
                        "${R.drawable.sam_claflin}",
                        "Sam Claflin",
                        "Mycroft Holmes"
                    )
                )
                add(
                    DetailTopCastEntity(
                        "${R.drawable.helena_bonham_carter}",
                        "Helena Bonham Carter",
                        "Eudoria Holmes"
                    )
                )
                add(
                    DetailTopCastEntity(
                        "${R.drawable.louis_partridge}",
                        "Louis Partridge",
                        "Lord Tewkesbury"
                    )
                )
            }
            "0003" -> detailTopCast.apply {
                add(
                    DetailTopCastEntity(
                        "${R.drawable.dylan_o_brien}",
                        "Dylan O'Brien",
                        "Joel Dawson"
                    )
                )
                add(
                    DetailTopCastEntity(
                        "${R.drawable.jessica_henwick}",
                        "Jessica Henwick",
                        "Aimee"
                    )
                )
                add(
                    DetailTopCastEntity(
                        "${R.drawable.michael_rooker}",
                        "Michael Rooker",
                        "Clyde"
                    )
                )
                add(
                    DetailTopCastEntity(
                        "${R.drawable.dan_ewing}",
                        "Dan Ewing",
                        "Cap"
                    )
                )
                add(
                    DetailTopCastEntity(
                        "${R.drawable.ariana_greenblatt}",
                        "Ariana Greenblatt",
                        "Minnow"
                    )
                )
            }
            "0004" -> detailTopCast.apply {
                add(
                    DetailTopCastEntity(
                        "${R.drawable.liu_yifei}",
                        "Liu Yifei",
                        "Hua Mulan"
                    )
                )
                add(
                    DetailTopCastEntity(
                        "${R.drawable.jet_li}",
                        "Jet Li",
                        "The Emperor"
                    )
                )
                add(
                    DetailTopCastEntity(
                        "${R.drawable.tzi_ma}",
                        "Tzi Ma",
                        "Hua Zhou"
                    )
                )
                add(
                    DetailTopCastEntity(
                        "${R.drawable.donnie_yen}",
                        "Donnie Yen",
                        "Commander Tung"
                    )
                )
                add(
                    DetailTopCastEntity(
                        "${R.drawable.gong_li}",
                        "Gong Li",
                        "Xian Niang"
                    )
                )
            }
            "0005" -> detailTopCast.apply {
                add(
                    DetailTopCastEntity(
                        "${R.drawable.frank_welker}",
                        "Frank Welker",
                        "Scooby-Doo"
                    )
                )
                add(
                    DetailTopCastEntity(
                        "${R.drawable.grey_delisle}",
                        "Grey DeLisle",
                        "Daphne Blake"
                    )
                )
                add(
                    DetailTopCastEntity(
                        "${R.drawable.matthew_lillard}",
                        "Matthew Lillard",
                        "Shaggy Rogers"
                    )
                )
                add(
                    DetailTopCastEntity(
                        "${R.drawable.kate_micucci}",
                        "Kate Micucci",
                        "Velma Dinkley"
                    )
                )
                add(
                    DetailTopCastEntity(
                        "${R.drawable.cassandra_peterson}",
                        "Cassandra Peterson",
                        "Elvira"
                    )
                )
            }
            "1000" -> detailTopCast.apply {
                add(
                    DetailTopCastEntity(
                        "${R.drawable.aliyah_royale}",
                        "Aliyah Royale",
                        "Iris Bennett"
                    )
                )
                add(
                    DetailTopCastEntity(
                        "${R.drawable.alexa_mansour}",
                        "Alexa Mansour",
                        "Hope Bennett"
                    )
                )
                add(
                    DetailTopCastEntity(
                        "${R.drawable.hal_cumpston}",
                        "Hal Cumpston",
                        "Silas Plaskett"
                    )
                )
                add(
                    DetailTopCastEntity(
                        "${R.drawable.nicolas_cantu}",
                        "Nicolas Cantu",
                        "Elton Ortiz"
                    )
                )
                add(
                    DetailTopCastEntity(
                        "${R.drawable.nico_tortorella}",
                        "Nico Tortorella",
                        "Felix Carlucci"
                    )
                )
            }
            "2000" -> detailTopCast.apply {
                add(
                    DetailTopCastEntity(
                        "${R.drawable.victoria_pedretti}",
                        "Victoria Pedretti",
                        "Dani Clayton"
                    )
                )
                add(
                    DetailTopCastEntity(
                        "${R.drawable.oliver_jackson_cohen}",
                        "Oliver Jackson-Cohen",
                        "Peter Quint"
                    )
                )
                add(
                    DetailTopCastEntity(
                        "${R.drawable.henry_thomas}",
                        "Henry Thomas",
                        "Henry Wingrave"
                    )
                )
                add(
                    DetailTopCastEntity(
                        "${R.drawable.amelia_eve}",
                        "Amelia Eve",
                        "Jamie"
                    )
                )
                add(
                    DetailTopCastEntity(
                        "${R.drawable.t_nia_miller}",
                        "T'Nia Miller",
                        "Hannah Grose"
                    )
                )
            }
            "3000" -> detailTopCast.apply {
                add(
                    DetailTopCastEntity(
                        "${R.drawable.lily_collins}",
                        "Lily Collins",
                        "Emily Cooper"
                    )
                )
                add(
                    DetailTopCastEntity(
                        "${R.drawable.ashley_park}",
                        "Ashley Park",
                        "Mindy Chen"
                    )
                )
                add(
                    DetailTopCastEntity(
                        "${R.drawable.philippine_leroy_beaulieu}",
                        "Philippine Leroy-Beaulieu",
                        "Sylvie"
                    )
                )
                add(
                    DetailTopCastEntity(
                        "${R.drawable.camille_razat}",
                        "Camille Razat",
                        "Camille"
                    )
                )
                add(
                    DetailTopCastEntity(
                        "${R.drawable.michel_biel}",
                        "Michel Biel",
                        "Fabien"
                    )
                )
            }
            "4000" -> detailTopCast.apply {
                add(
                    DetailTopCastEntity(
                        "${R.drawable.ichikawa_taichi}",
                        "Ichikawa Taichi",
                        "Bam"
                    )
                )
                add(
                    DetailTopCastEntity(
                        "${R.drawable.youko_hikasa}",
                        "Youko Hikasa",
                        "Karen"
                    )
                )
                add(
                    DetailTopCastEntity(
                        "${R.drawable.kenta_miyake}",
                        "Kenta Miyake",
                        "Rak Wraithraiser"
                    )
                )
                add(
                    DetailTopCastEntity(
                        "${R.drawable.kazuyuki_okitsu}",
                        "Kazuyuki Okitsu",
                        "Evan Edroch"
                    )
                )
                add(
                    DetailTopCastEntity(
                        "${R.drawable.saori_hayami}",
                        "Saori Hayami",
                        "Rachel"
                    )
                )
            }
            "5000" -> detailTopCast.apply {
                add(
                    DetailTopCastEntity(
                        "${R.drawable.yuichi_nakamura}",
                        "Yuichi Nakamura",
                        "Hōtarō Oreki"
                    )
                )
                add(
                    DetailTopCastEntity(
                        "${R.drawable.satomi_satou}",
                        "Satomi Satou",
                        "Chitanda Eru"
                    )
                )
                add(
                    DetailTopCastEntity(
                        "${R.drawable.daisuke_sakaguchi}",
                        "Daisuke Sakaguchi",
                        "Satoshi Fukube"
                    )
                )
                add(
                    DetailTopCastEntity(
                        "${R.drawable.ai_kayano}",
                        "Ai Kayano",
                        "Mayaka Ibara"
                    )
                )
                add(
                    DetailTopCastEntity(
                        "${R.drawable.yukana}",
                        "Yukana",
                        "Fuyumi Irisu"
                    )
                )
            }
        }
        return detailTopCast
    }
}
