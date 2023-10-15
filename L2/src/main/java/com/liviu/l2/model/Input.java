package com.liviu.l2.model;

import jakarta.servlet.http.Part;

public class Input {
    private Part filePart;

    public Part getFilePart() {
        return filePart;
    }

    public void setFilePart(Part filePart) {
        this.filePart = filePart;
    }
}
