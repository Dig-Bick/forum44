package com.example.forum4.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName statistics
 */
@Data
public class Statistic implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private Integer userCount;

    /**
     * 
     */
    private Integer postCount;

    /**
     * 
     */
    private Integer activeUserCount;

    /**
     * 
     */
    private Integer viewsCount;

    /**
     * 
     */
    private Date lastUpdated;

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
        Statistic other = (Statistic) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserCount() == null ? other.getUserCount() == null : this.getUserCount().equals(other.getUserCount()))
            && (this.getPostCount() == null ? other.getPostCount() == null : this.getPostCount().equals(other.getPostCount()))
            && (this.getActiveUserCount() == null ? other.getActiveUserCount() == null : this.getActiveUserCount().equals(other.getActiveUserCount()))
            && (this.getViewsCount() == null ? other.getViewsCount() == null : this.getViewsCount().equals(other.getViewsCount()))
            && (this.getLastUpdated() == null ? other.getLastUpdated() == null : this.getLastUpdated().equals(other.getLastUpdated()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserCount() == null) ? 0 : getUserCount().hashCode());
        result = prime * result + ((getPostCount() == null) ? 0 : getPostCount().hashCode());
        result = prime * result + ((getActiveUserCount() == null) ? 0 : getActiveUserCount().hashCode());
        result = prime * result + ((getViewsCount() == null) ? 0 : getViewsCount().hashCode());
        result = prime * result + ((getLastUpdated() == null) ? 0 : getLastUpdated().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userCount=").append(userCount);
        sb.append(", postCount=").append(postCount);
        sb.append(", activeUserCount=").append(activeUserCount);
        sb.append(", viewsCount=").append(viewsCount);
        sb.append(", lastUpdated=").append(lastUpdated);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}