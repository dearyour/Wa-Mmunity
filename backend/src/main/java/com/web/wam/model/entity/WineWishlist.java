package com.web.wam.model.entity;

import lombok.*;
<<<<<<< HEAD

=======
>>>>>>> b4b9e52929aeeff9195938f468e3e051f65fff79
import javax.persistence.*;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "wine_wishlist")
public class WineWishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wish_id", nullable = false)
    private Integer id;

    @Column(name = "member_id", nullable = false)
    private Integer memberId;

    @Column(name = "wine_id", nullable = false)
    private Integer wineId;
}