package com.eventersapp.gojek_trending.ui.fakeData

import com.eventersapp.gojek_trending.domain.TrendingModal


object TrendingRepo {

    val FAKE_REPO = listOf(
        TrendingModal(
            "One", "Vikram", "https://image.flaticon.com/icons/png/512/160/160138.png",
            "www.github.com", "Doing great", "Kotlin", "Yellow",
            1220, 220
        ),
        TrendingModal(
            "TWo", "Singh", "https://image.flaticon.com/icons/png/512/160/160138.png",
            "www.github.com", "Doing great", "Java", "Gree",
            1320, 220
        ),
        TrendingModal(
            "Three", "Hari", "https://image.flaticon.com/icons/png/512/160/160138.png",
            "www.github.com", "Doing great", "Python", "Pink",
            1210, 220
        )
    )
}


/*
[
{
    "author": "monicahq",
    "name": "monica",
    "avatar": "https://github.com/monicahq.png",
    "url": "https://github.com/monicahq/monica",
    "description": "Personal CRM. Remember everything about your friends and family.",
    "language": "PHP",
    "languageColor": "#4F5D95",
    "stars": 8342,
    "forks": 1029,
    "currentPeriodStars": 210
},
{
    "author": "naptha",
    "name": "tesseract.js",
    "avatar": "https://github.com/naptha.png",
    "url": "https://github.com/naptha/tesseract.js",
    "description": "Pure Javascript OCR for more than 100 Languages 📖🎉🖥",
    "language": "JavaScript",
    "languageColor": "#f1e05a",
    "stars": 19068,
    "forks": 1293,
    "currentPeriodStars": 671
}
]*/
