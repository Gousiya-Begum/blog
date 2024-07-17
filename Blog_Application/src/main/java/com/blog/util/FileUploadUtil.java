package com.blog.util;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileUploadUtil {

    private static final String UPLOAD_DIRECTORY = "uploads";

    public static String uploadFile(HttpServletRequest request, String fieldName) {
        String uploadPath = request.getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;

        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        String fileName = null;

        if (ServletFileUpload.isMultipartContent(request)) {
            try {
                List<FileItem> formItems = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);

                for (FileItem item : formItems) {
                    if (!item.isFormField() && item.getFieldName().equals(fieldName)) {
                        fileName = Paths.get(item.getName()).getFileName().toString();
                        String filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);

                        // Save the file on disk
                        item.write(storeFile);
                        break;
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return UPLOAD_DIRECTORY + "/" + fileName;
    }
}
