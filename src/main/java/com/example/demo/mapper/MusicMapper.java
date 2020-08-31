package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.MusicEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MusicMapper extends BaseMapper<MusicEntity> {

    @Select("select * from music where musicCategory =#{musicCategory} order by rand() limit 8")//将视频标记为1的正常视频全部查询出
    public List<MusicEntity> musicListByRand(String musicCategory);


    @Select("select * from music order by musicClick+musicComment*10 desc limit #{limit}")
    public List<MusicEntity> hotMusicList(int limit);

}
