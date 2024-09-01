package com.spandigital.dto;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

public record TeamNameAndRank(List<Map<String, Integer>> teamRankList, HashSet<String> teamNames) {
}
