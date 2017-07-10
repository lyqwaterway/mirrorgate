/*
 * Copyright 2017 Banco Bilbao Vizcaya Argentaria, S.A.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Product Increment model
 *
 * @param {Number} featuresNumber     Number of features in the PI

 */
function ProgramIncrement(completedFeatures, features, stories, products){
  this.completedFeatures = completedFeatures;
  this.features = features;
  this.stories = stories;
  this.products = products;
  this.stats = {
    featureCount: features ? features.length : 0,
    completedFeatureCount: completedFeatures ? completedFeatures.length : 0
  };
}