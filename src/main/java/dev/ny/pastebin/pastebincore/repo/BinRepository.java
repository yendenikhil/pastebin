package dev.ny.pastebin.pastebincore.repo;

import dev.ny.pastebin.pastebincore.bean.BinEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BinRepository extends PagingAndSortingRepository<BinEntity, Long> {
}
