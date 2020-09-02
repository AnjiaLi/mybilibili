let firstStar = true
window.onload = function () {
    let page = window.location.pathname.slice(1)
    showPlayList()
    if (page == "list") {
        firstStar = false
        initList()
    } else if (page == "music") {
        initMusic()
    }
}

function initList() {
    let myAudio = document.querySelector("#myAudio");
    myAudio.addEventListener("ended", () => playMusic(true))
    myAudio.addEventListener("abort", () => console.log("mp3 abort"))
    myAudio.addEventListener("error", () => console.log("mp3 error"))
    myAudio.addEventListener("stalled", () => console.log("mp3 stalled"))
    playMusic(false)
}

function initMusic() {
    let addButton = document.querySelector(".add-button")
    let starButton = document.querySelector(".star-button")
    addButton.addEventListener("click", () => addMusic(JSON.parse(document.querySelector(".music-info").textContent)))
    starButton.addEventListener("click", () => {
        let musicID = window.location.search
        musicID = musicID.slice(musicID.lastIndexOf("=") + 1)
        starMusic(musicID, 0)
    })
    starButton.click()
}

function showPlayList(newList, newIndex) {
    let table = document.querySelector("#play-list")
    if (table == null) return
    let playList = newList
    if (playList == undefined) playList = getPlayList()
    for (let i = 0, tr, name, singer, time, add, star, del, aaa, opt, imggg; i < playList.length; i++) {
        tr = document.createElement("tr")
        name = document.createElement("td")
        name.className = "music-name"
        singer = document.createElement("td")
        singer.textContent = playList[i].singer
        singer.className = "music-singer"
        time = document.createElement("td")
        time.textContent = playList[i].time
        time.className = "music-time"
        add = document.createElement("button")
        add.className = "add-button"
        add.onclick = () => {
            if (newIndex == undefined)
                addMusic(playList[i], i)
            else
                addMusic(playList[i], newIndex)
        }
        imggg = document.createElement("img")
        imggg.src = imgSrccc.add
        add.appendChild(imggg)
        // star = document.createElement("input")
        // star.type = "button"
        // star.value = "收藏"
        // star.className = "star-button"
        // star.onclick = () => {
        //     starMusic(playList[i].id, i)
        // }
        del = document.createElement("button")
        del.className = "del-button"
        del.onclick = () => {
            if (newIndex == undefined)
                delMusic(i)
            else
                delMusic(newIndex)
        }
        imggg = document.createElement("img")
        imggg.src = imgSrccc.del
        del.appendChild(imggg)
        opt = document.createElement("td")
        opt.className = "music-opt"
        aaa = document.createElement("a")
        aaa.href = "/music?ID=" + playList[i].id
        aaa.textContent = playList[i].name
        name.appendChild(aaa)
        opt.appendChild(add)
        opt.appendChild(del)
        tr.appendChild(name)
        tr.appendChild(singer)
        tr.appendChild(time)
        tr.appendChild(opt)
        table.children[0].appendChild(tr)
    }
}

function getPlayList() {
    let playList = localStorage.getItem("playList")
    if (playList == null) playList = []
    else playList = JSON.parse(playList)
    return playList
}

function playMusic(next) {
    let myAudio = document.querySelector("#myAudio");
    let playList = getPlayList()
    let index = localStorage.getItem("index")
    if (next) {
        index = (index * 1 + 1) % playList.length
        localStorage.setItem("index", index)
    }
    try {
        console.log(playList[index].address);
        myAudio.src = playList[index].address
        myAudio.load();
        myAudio.play();
        showNowMusic(playList[index].name, index)
    } catch (error) {
        console.error(error);
        myAudio.src = ""
        showNowMusic("无", -1)
    }
}

function addMusic(music, existsIndex) {
    let playList = getPlayList()
    // let prefix = window.location.protocol + "//" + window.location.host + "/"
    let newMusic, index
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
    if (existsIndex != undefined)
        index = existsIndex
    else
        index = playList.findIndex(e => e.id == newMusic.id)
    if (index == -1) {
        playList.push(newMusic)
        localStorage.setItem("playList", JSON.stringify(playList))
        localStorage.setItem("index", playList.length - 1)
        showPlayList([newMusic], playList.length - 1)
    } else {
        localStorage.setItem("index", index)
    }
    playMusic(false)
}

var imgSrccc = {
    add: "webIndex/images/add.png",
    star: "webIndex/images/starbefore.png",
    starred: "webIndex/images/starafter.png",
    del: "webIndex/images/delete.png",
}
function starMusic(id, index) {
    let starButton = document.querySelectorAll(".star-button")[index].children[0]
    let unFav = false
    if (starButton.src.slice(starButton.src.indexOf("webI")) == imgSrccc.starred)
        if (confirm("删除收藏？"))
            unFav = true
        else return
    $.ajax({
        type: "post",
        url: "/music/starMusic",
        data: {
            musicID: id,
            unFav: unFav
        },
        success: function (result) {
            if (result == "false") {
                if (!firstStar)
                    if (confirm("请登录"))
                        window.location.href = "/clientLogin"
            } else {
                if (unFav)
                    starButton.src = imgSrccc.star
                else
                    starButton.src = imgSrccc.starred
            }
            firstStar = false
        },
        error: function (e) {
            console.log(e.status);
            console.log(e.responseText);
        }
    });
}

function delMusic(delIndex) {
    let playList = getPlayList()
    playList.splice(delIndex, 1)
    localStorage.setItem("playList", JSON.stringify(playList))
    let index = localStorage.getItem("index")
    index = index < delIndex ? index : index - 1
    localStorage.setItem("index", index)
    clearPlayList()
    showPlayList()
    if (index == -1) playMusic(false)
    else if (index * 1 + 1 == delIndex)
        if (index == playList.length - 1) playMusic(false)
        else playMusic(true)
}

function clearPlayList() {
    let table = document.querySelector("#play-list")
    if (table == null) return
    table.children[0].innerHTML = "<tr><th>歌名</th><th>歌手</th><th>时长</th><th class='more-info'>操作</th></tr>"
}

let oldIndex = 0
function showNowMusic(name, index) {
    document.querySelector("#now-span").textContent = name
    let tc = document.querySelector("#play-list").children[0]
    if (index != -1) {
        if (tc.children[oldIndex] != undefined)
            tc.children[oldIndex].id = ""
        oldIndex = index * 1 + 1
        tc.children[oldIndex].id = "now-table";
    }
}

function delAll() {
    clearPlayList()
    localStorage.removeItem("playList")
    localStorage.setItem("index", 0)
    playMusic(false)
}
