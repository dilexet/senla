package com.senla.service;

import com.senla.entity.Container;
import com.senla.enums.ContainerSize;

public interface IContainerService {
    Container createContainer(ContainerSize containerSize);
    void addWater(Container container, float liters) throws Exception;
}
