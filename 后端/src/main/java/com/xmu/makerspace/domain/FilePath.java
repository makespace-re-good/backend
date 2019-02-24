package com.xmu.makerspace.domain;

import javax.persistence.*;

@Entity
@Table(name = "file_path", schema = "makerspace", catalog = "")
public class FilePath {
    private String teamid;
    private int pathid;
    private String path;

    @Basic
    @Column(name = "TEAMID")
    public String getTeamid() {
        return teamid;
    }

    public void setTeamid(String teamid) {
        this.teamid = teamid;
    }

    @Id
    @Column(name = "PATHID")
    public int getPathid() {
        return pathid;
    }

    public void setPathid(int pathid) {
        this.pathid = pathid;
    }

    @Basic
    @Column(name = "PATH")
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FilePath filePath = (FilePath) o;

        if (pathid != filePath.pathid) return false;
        if (teamid != null ? !teamid.equals(filePath.teamid) : filePath.teamid != null) return false;
        if (path != null ? !path.equals(filePath.path) : filePath.path != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = teamid != null ? teamid.hashCode() : 0;
        result = 31 * result + pathid;
        result = 31 * result + (path != null ? path.hashCode() : 0);
        return result;
    }
}
