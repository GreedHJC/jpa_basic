package hellojpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * description
 *
 * @author : jcHwang
 */
@Entity
public class Product {

  @Id @GeneratedValue
  private Long id;
}
