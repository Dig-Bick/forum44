package com.example.forum4.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

import javax.persistence.*;


/**
 * 
 * @TableName comments
 */
@Data
public class Comment implements Serializable {
    /**
     * 
     */
    private Integer commentId;

    /**
     * 
     */
    private Integer postId;

    /**
     * 
     */
    private Integer userId;

    /**
     * 
     */
    private Integer parentCommentId;

    /**
     * 
     */
    private String content;

    /**
     * 
     */
    private Date createdAt;
    private String username;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "parent_comment_id", nullable = true)
    private Comment parentComment;

    @OneToMany(mappedBy = "parentComment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> replies;

    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Comment other = (Comment) that;
        return (this.getCommentId() == null ? other.getCommentId() == null : this.getCommentId().equals(other.getCommentId()))
            && (this.getPostId() == null ? other.getPostId() == null : this.getPostId().equals(other.getPostId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getParentCommentId() == null ? other.getParentCommentId() == null : this.getParentCommentId().equals(other.getParentCommentId()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCommentId() == null) ? 0 : getCommentId().hashCode());
        result = prime * result + ((getPostId() == null) ? 0 : getPostId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getParentCommentId() == null) ? 0 : getParentCommentId().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", commentId=").append(commentId);
        sb.append(", postId=").append(postId);
        sb.append(", userId=").append(userId);
        sb.append(", parentCommentId=").append(parentCommentId);
        sb.append(", content=").append(content);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", username=").append(username);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}