import esRecette from './es-recette';
const index = 'local_users';
const handleElasticsearchError = (error) => {
    if (error.status === 404) {
        throw new Error('User Not Found', 404);
    }
    throw new Error(error.msg, error.status || 500);
};
const getAll = () => esRecette.search({
    index,
}).then(response => response).catch((error) => {
    handleElasticsearchError(error);
});
const store = user => esRecette.index({
    index,
    refresh: 'true',
    body: user,
}).then(response => response.status).catch((error) => {
    handleElasticsearchError(error);
});
const getUser = recipeName => esRecette.search({
    index,
    body: {
        "query": {
            "match": {
                "firstName": {
                    "query": recipeName
                }
            }
        }
    },
}).then(response => { response; })
    .catch((error) => {
        handleElasticsearchError(error);
    });
export default {
    getUser,
    store,
    getAll,
}