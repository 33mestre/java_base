/*
 * Copyright (c) 2024, Shelson Ferrari
 *
 * Licensed under the MIT License and the Apache License, Version 2.0 (the "Licenses"); you may not use this file except in
 * compliance with the Licenses. You may obtain a copy of the Licenses at
 *
 * MIT License:
 * https://opensource.org/licenses/MIT
 *
 * Apache License, Version 2.0:
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the Licenses is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See
 * the Licenses for the specific language governing permissions and limitations under the Licenses.
 */
package com.shelson.domain.repository;

import com.shelson.domain.model.CurrencyConversion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for the entity {@link CurrencyConversion}.
 * 
 * This repository extends {@link JpaRepository}, providing CRUD methods for the entity {@link CurrencyConversion}.
 * 
 * @version 0.6.3
 * @since 2024-07-24
 * 
 * @see com.shelson.domain.model.CurrencyConversion
 * @see org.springframework.data.jpa.repository.JpaRepository
 */
@Repository
public interface CurrencyConversionRepository extends JpaRepository<CurrencyConversion, Long> {
}
