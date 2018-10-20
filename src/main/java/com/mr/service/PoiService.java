package com.mr.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;

/**
 * Created by yaodd on 2018/10/20.
 */
public interface PoiService {
    void export(ServletOutputStream outputStream);

    void importPoi(String fileName, MultipartFile file);
}
