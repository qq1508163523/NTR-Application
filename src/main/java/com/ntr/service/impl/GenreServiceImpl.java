package com.ntr.service.impl;

import com.ntr.entity.Genre;
import com.ntr.mapper.GenreMapper;
import com.ntr.service.GenreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Vincent Li
 * @since 2022-01-10
 */
@Service
public class GenreServiceImpl extends ServiceImpl<GenreMapper, Genre> implements GenreService {


    @Override
    public List<Genre> getAllGenres() {
        return baseMapper.selectList(null);
    }
}
