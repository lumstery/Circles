package com.jarst.service;

import com.jarst.dto.AssetManifest;

public interface AssetManifestService {
    AssetManifest fetchAssetManifest();

    void invalidateCache();
}
