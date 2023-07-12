package com.joseph.kmmsocialapp.android.common.fake_data

data class Post(
    val id: String,
    val text: String,
    val imageUrl: String,
    val createdAt: String,
    val likesCount: Int,
    val commentCount: Int,
    val authorId: Int,
    val authorName: String,
    val authorImage: String,
    val isLiked: Boolean = false,
    val isOwnPost: Boolean = false
)


val samplePosts = listOf(
    Post(
        id = "11",
        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
        imageUrl = "https://sun9-67.userapi.com/c4741/u112406626/-6/x_706b6a3c.jpg",
        createdAt = "20 min",
        likesCount = 12,
        commentCount = 3,
        authorId = 1,
        authorName = "Mr Dip",
        authorImage = "https://picsum.photos/200"
    ),
    Post(
        id = "12",
        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
        imageUrl = "https://townsquare.media/site/442/files/2012/05/bender.jpg?w=1200&h=0&zc=1&s=0&a=t&q=89",
        createdAt = "May 03, 2023",
        likesCount = 121,
        commentCount = 23,
        authorId = 2,
        authorName = "John Cena",
        authorImage = "https://picsum.photos/200"
    ),
    Post(
        id = "13",
        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
        imageUrl = "https://yt3.googleusercontent.com/Io8UGoybnX-C6bXuvuxNKGG6Mz-8cfdkO5wKbdJRE7IdwO7jrwbWU9OSE96vESg9Y1ofx-OOUfg=s900-c-k-c0x00ffffff-no-rj",
        createdAt = "Apr 12, 2023",
        likesCount = 221,
        commentCount = 41,
        authorId = 3,
        authorName = "Cristiano",
        authorImage = "https://picsum.photos/200"
    ),
    Post(
        id = "14",
        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
        imageUrl = "https://1.bp.blogspot.com/-ErMHDuaxo6Y/XUU203uHh3I/AAAAAAAAbNo/XaWcpmF7XporvyZcq58C8e7xUQi-J3GJgCLcBGAs/s1600/Screenshot%2B%2528520%2529.png",
        createdAt = "Mar 31, 2023",
        likesCount = 90,
        commentCount = 13,
        authorId = 3,
        authorName = "Cristiano",
        authorImage = "https://picsum.photos/200"
    ), Post(
        id = "15",
        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
        imageUrl = "https://www.wallpaperflare.com/static/956/184/864/rick-and-morty-adult-swim-cartoon-rick-sanchez-wallpaper.jpg",
        createdAt = "Jan 30, 2023",
        likesCount = 121,
        commentCount = 31,
        authorId = 4,
        authorName = "L. James",
        authorImage = "https://picsum.photos/200"
    ),
)