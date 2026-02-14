package com.example.mcp.model;

import java.util.Date;
import java.util.List;

public class PromptTemplate {
    private String id;
    private String name;
    private String description;
    private String content;
    private String category;
    private String createdBy;
    private Date createdAt;
    private Date updatedAt;
    private int version;
    private List<PromptVersion> versions;
    private boolean isPublic;
    private List<String> tags;

    // Constructors
    public PromptTemplate() {
        this.createdAt = new Date();
        this.updatedAt = new Date();
        this.version = 1;
        this.isPublic = false;
    }

    public PromptTemplate(String name, String description, String content, String category) {
        this();
        this.name = name;
        this.description = description;
        this.content = content;
        this.category = category;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { 
        this.name = name;
        this.updatedAt = new Date();
    }

    public String getDescription() { return description; }
    public void setDescription(String description) { 
        this.description = description;
        this.updatedAt = new Date();
    }

    public String getContent() { return content; }
    public void setContent(String content) { 
        this.content = content;
        this.updatedAt = new Date();
        this.version++;
    }

    public String getCategory() { return category; }
    public void setCategory(String category) { 
        this.category = category;
        this.updatedAt = new Date();
    }

    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public Date getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Date updatedAt) { this.updatedAt = updatedAt; }

    public int getVersion() { return version; }
    public void setVersion(int version) { this.version = version; }

    public List<PromptVersion> getVersions() { return versions; }
    public void setVersions(List<PromptVersion> versions) { this.versions = versions; }

    public boolean isPublic() { return isPublic; }
    public void setPublic(boolean aPublic) { isPublic = aPublic; }

    public List<String> getTags() { return tags; }
    public void setTags(List<String> tags) { this.tags = tags; }

    // Inner class for version tracking
    public static class PromptVersion {
        private int versionNumber;
        private String content;
        private String modifiedBy;
        private Date modifiedAt;
        private String changeDescription;

        public PromptVersion() {}

        public PromptVersion(int versionNumber, String content, String modifiedBy, String changeDescription) {
            this.versionNumber = versionNumber;
            this.content = content;
            this.modifiedBy = modifiedBy;
            this.changeDescription = changeDescription;
            this.modifiedAt = new Date();
        }

        // Getters and Setters
        public int getVersionNumber() { return versionNumber; }
        public void setVersionNumber(int versionNumber) { this.versionNumber = versionNumber; }

        public String getContent() { return content; }
        public void setContent(String content) { this.content = content; }

        public String getModifiedBy() { return modifiedBy; }
        public void setModifiedBy(String modifiedBy) { this.modifiedBy = modifiedBy; }

        public Date getModifiedAt() { return modifiedAt; }
        public void setModifiedAt(Date modifiedAt) { this.modifiedAt = modifiedAt; }

        public String getChangeDescription() { return changeDescription; }
        public void setChangeDescription(String changeDescription) { this.changeDescription = changeDescription; }
    }
}