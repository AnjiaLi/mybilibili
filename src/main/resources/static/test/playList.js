window.onload = function () {
    let page = window.location.pathname.slice(1)
    if (page == "list") {
        initList()
    } else if (page == "music") {
        initMusic()
    }
    showPlayList()
}

function initList() {
    // let musicList = getMusicList();
    let myAudio = document.querySelector("#myAudio");
    // localStorage.setItem("index", 0);
    // localStorage.setItem("playList", JSON.stringify(musicList));
    myAudio.addEventListener("ended", playMusic(true))
}

function initMusic() {
    let addButton = document.querySelector(".add-button")
    let starButton = document.querySelector(".star-button")
    addButton.addEventListener("click", addMusic(JSON.parse(document.querySelector(".music-info").textContent)))
    starButton.addEventListener("click", () => {
        let musicID = window.location.search
        musicID = musicID.slice(musicID.lastIndexOf("=") + 1)
        starMusic(musicID, 0, true)
    })
    starButton.click()
}

function showPlayList() {
    let table = document.querySelector("#play-list")
    let playList = getPlayList()
    for (let i = 0, tr, name, singer, time, add, star; i < playList.length; i++) {
        tr = document.createElement("tr")
        name = document.createElement("td")
        name.textContent = playList[i].name
        name.className = "music-name"
        singer = document.createElement("td")
        singer.textContent = playList[i].singer
        singer.className = "music-singer"
        time = document.createElement("td")
        time.textContent = playList[i].time
        time.className = "music-time"
        add = document.createElement("input")
        add.type = "button"
        add.value = "播放"
        add.className = "add-button"
        add.onclick = () => {
            addMusic(playList[i])
        }
        star = document.createElement("input")
        star.type = "button"
        star.value = "收藏"
        star.className = "star-button"
        star.onclick = () => {
            starMusic(playList[i].id, i, false)
        }
        tr.appendChild(name)
        tr.appendChild(singer)
        tr.appendChild(time)
        tr.appendChild(add)
        tr.appendChild(star)
        table.children[0].appendChild(tr)
    }
}

function getPlayList() {
    let playList = localStorage.getItem("playList")
    if (playList == null) playList = []
    else playList = JSON.parse(playList)
    return playList
}

function getMusicList() {
    let musicAddress = ["music/水月陵 - それは、とある王国の物語.mp3", "music/樋口秀樹 - 砂漠の花.mp3", "music/Metomate - nostalgia.mp3"
        , "music/Peak A Soul+ - 町娘になれたら.mp3", "music/山下航生,rian,朝霧はやと - story.mp3"]
    let musicList = []
    // let prefix = "http://localhost:8080/"
    let prefix = window.location.protocol + "//" + window.location.host + "/"
    for (let i = 0, str; i < musicAddress.length; i++) {
        str = musicAddress[i];
        musicList.push({
            name: str.slice(str.lastIndexOf("/") + 1, str.lastIndexOf(".")),
            singer: "test",
            address: prefix + str
        })
    }
    return musicList
}

function playMusic(next) {
    let myAudio = document.querySelector("#myAudio");
    let playList = getPlayList()
    let index = localStorage.getItem("index")
    if (next) {
        index = (index * 1 + 1) % playList.length
        localStorage.setItem("index", index)
    }
    console.log(playList[index].address);
    myAudio.src = playList[index].address
    myAudio.load();
    myAudio.play();
}

function addMusic(music) {
    let playList = getPlayList()
    // let prefix = window.location.protocol + "//" + window.location.host + "/"
    let newMusic
    if (music.musicName == undefined) {
        newMusic = music
    } else {
        newMusic = {
            name: music.musicName,
            singer: music.singer,
            time: music.musicTime,
            id: music.musicID,
            address: music.musicAddress,
        }
    }
    let index = playList.findIndex(e => e.id == newMusic.id)
    if (index == -1) {
        playList.push(newMusic)
        localStorage.setItem("playList", JSON.stringify(playList))
        localStorage.setItem("index", playList.length - 1)
    } else {
        localStorage.setItem("index", index)
    }
    playMusic(false)
}

function starMusic(id, index, first) {
    let starButton = document.querySelectorAll(".star-button")[index]
    let unFav = false
    if (starButton.value == "已收藏") unFav = true
    $.ajax({
        type: "post",
        url: "/music/starMusic",
        data: {
            musicID: id,
            unFav: unFav
        },
        success: function (result) {
            if (result == "false") {
                if (!first)
                    alert("请登录")
            } else {
                if (unFav)
                    starButton.value = "收藏"
                else
                    starButton.value = "已收藏"
            }
        },
        error: function (e) {
            console.log(e.status);
            console.log(e.responseText);
        }
    });
}