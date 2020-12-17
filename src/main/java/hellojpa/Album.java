package hellojpa;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * description
 *
 * @author : jcHwang
 */
@Entity
@DiscriminatorValue("A")
public class Album extends Item {

  private String artist;


  public String getArtist() {
    return artist;
  }

  public void setArtist(String artist) {
    this.artist = artist;
  }
}
