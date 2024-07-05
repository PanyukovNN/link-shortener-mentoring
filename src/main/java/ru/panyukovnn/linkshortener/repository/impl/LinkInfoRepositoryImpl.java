package ru.panyukovnn.linkshortener.repository.impl;

import org.springframework.stereotype.Service;
import ru.panyukovnn.linkshortener.exception.NotFoundException;
import ru.panyukovnn.linkshortener.model.LinkInfo;
import ru.panyukovnn.linkshortener.repository.LinkInfoRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class LinkInfoRepositoryImpl implements LinkInfoRepository {

    private final Map<String, LinkInfo> storage = new ConcurrentHashMap<>();

    @Override
    public Optional<LinkInfo> findByShortLink(String shortLint) {
        return Optional.ofNullable(storage.get(shortLint));
    }

    @Override
    public LinkInfo saveShortLink(LinkInfo linkInfo) {
        linkInfo.setId(UUID.randomUUID());

        storage.put(linkInfo.getShortLink(), linkInfo);

        return linkInfo;
    }

    @Override
    public List<LinkInfo> getAll() {
        return storage.values().stream()
            .toList();
    }

    @Override
    public void deleteById(UUID id) {
        String shortLinkToRemove = storage.entrySet().stream()
            .filter(it -> it.getValue().getId().equals(id))
            .findFirst()
            .map(Map.Entry::getKey)
            .orElseThrow(() -> new NotFoundException("Не найдена короткая ссылка для удаления по id: " + id));

        storage.remove(shortLinkToRemove);
    }
}
