https://www.openbrewerydb.org/documentation/01-listbreweries

Testing approach:
- Black box testing (no code access) using custom scripts (described in the documentation)
Testing techniques:
- Boundary value analysis (where we have constrained conditions, such as the per_page parameter)
- Equivalence classes (where we can separate testing data into valid and invalid, for example by_city, by_name, by_state, by_postal, by_type)
- Cause / Effect ('page')

My thoughts on how to test in a checklist:
Positive
1. "by city"
- Get breweries by existing city and make sure that all the elements of the result relate to this city
2. "by_name"
- Get breweries by existing word and make sure all result elements contain that word in their titles
3. "by state"
- Get the brewery by existing state and make sure that all the elements of the result have exactly this state
4. "by_postal"
- Get the brewery by existing postal_code and make sure that all result elements have exactly this code
5. "by type"
- Get breweries for one of the available types and check that all elements of the result are of this type
6. "page"
- Get all breweries -> open page 2 and save it as expected -> execute {baseUrl} / breweries?page = 2 and compare the result with the expected result
7. "per_page"
- Get breweries with per_page = 1
- Get breweries with per_page = 50
8. "sort"
- Get breweries sorted by one field in ascending order
- Get breweries sorted by one field in descending order
- Get breweries sorted by two fields in ascending order
- Get breweries sorted by two fields in descending order

Negative
1. "by city"
- Get breweries by nonexistent city and check that the result is empty
2. "by_name"
- Get breweries by special symbol and check if the result is empty
3. "by state"
- Get breweries by nonexistent state and check that the result is empty
4. "by_postal"
- Get breweries by non-existent postal_code and check the result is empty
5. "by type"
- Get breweries by any unknown type and check if the result is empty
6. "page"
- Get all breweries on page '-1' -> expect we got the first page by default
7. "per_page"
- Get breweries with per_page = 0
- Get breweries with per_page = 51




