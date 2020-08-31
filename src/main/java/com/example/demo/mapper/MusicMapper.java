package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.MusicEntity;
import com.example.demo.entity.VideoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MusicMapper extends BaseMapper<MusicEntity> {

    @Select("select * from video where videocAtegory =#{videocAtegory} order by rand() limit 8")//将视频标记为1的正常视频全部查询出
    public List<VideoEntity> videolist(String videocAtegory);


    @Select("select * from music order by musicClick+musicComment*10 desc limit #{limit}")
    public List<MusicEntity> hotMusicList(int limit);

}
