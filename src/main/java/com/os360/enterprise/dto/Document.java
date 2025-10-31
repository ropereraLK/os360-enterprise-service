package com.os360.enterprise.dto;

import com.os360.enterprise.enumurations.DocumentType;

import java.time.LocalDate;
import java.util.UUID;

public class Document {
    private UUID id;
    private Party party;
    private String fileName;
    private String filePath;
    private LocalDate uploadDate;
    private DocumentType documentType;
    private String otherDocumentType;
}
