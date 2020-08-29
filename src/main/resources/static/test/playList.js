window.onload = function () {
    let musicList = getMusicList();
    let myAudio = document.querySelector("#myAudio");
    localStorage.setItem("index", 0);
    localStorage.setItem("playList", JSON.stringify(musicList));
    myAudio.addEventListener("ended", () => {
        let index = localStorage.getItem("index")
        index = (index * 1 + 1) % musicList.length
        console.log(musicList[index].address);
        myAudio.src = musicList[index].address
        localStorage.setItem("index", index)
        myAudio.load();
        myAudio.play();
    })
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