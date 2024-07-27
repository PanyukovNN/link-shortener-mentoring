package ru.panyukovnn.linkshortener.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.panyukovnn.linkshortener.model.LinkInfo;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

public interface LinkInfoRepository extends JpaRepository<LinkInfo, UUID> {

    @Query(value = """
        FROM LinkInfo li
        WHERE (:linkPart IS NULL OR li.link LIKE '%' || :linkPart || '%')
        AND (cast(:endTimeFrom as date) IS NULL OR li.endTime >= :endTimeFrom)
        AND (cast(:endTimeTo as date) IS NULL OR li.endTime >= :endTimeTo)
        AND (:descriptionPart IS NULL OR li.description LIKE '%' || :descriptionPart || '%')
        AND (:active IS NULL OR li.active = :active)
        """)
    Page<LinkInfo> findByFilter(String linkPart,
                                ZonedDateTime endTimeFrom,
                                ZonedDateTime endTimeTo,
                                String descriptionPart,
                                Boolean active,
                                Pageable pageable);

    Optional<LinkInfo> findByShortLinkAndActiveTrueAndEndTimeIsAfter(String shortLink, ZonedDateTime endTimeAfter);

    @Query(value = """
        UPDATE LinkInfo li
        SET li.openingCount = li.openingCount + 1
        WHERE li.shortLink = :shortLink
        """)
    @Modifying
    @Transactional
    void incrementOpeningCountByShortLink(String shortLink);
}
