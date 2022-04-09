function onSearchSubmit() {
    const query = document.getElementById("search-input").value
    const cardIds = {}

    fetch("/api")
        .then(response => response.json())
        .then(data => {
            for (const [key, title] of Object.entries(data)) {
                cardIds[key] = title
            }

            removeAllChildNodes(document.getElementById("cards-container"))

            for (const [cardId, cardTitle] of Object.entries(cardIds)) {
                if (query.trim() === "" || cardTitle.toLowerCase().includes(query.toLowerCase())) {
                    fetch(`/api/${cardId}`)
                        .then(response => response.json())
                        .then(data => {
                            insertCard(data)
                        })
                }
            }
        })
}

function removeAllChildNodes(parent) {
    while (parent.firstChild) {
        parent.removeChild(parent.firstChild);
    }
}

function insertCard(card) {
    const cardElement = document.createElement("div")
    cardElement.className = "card"

    cardElement.append(
        document.createElement("h2").appendChild(document.createTextNode(card.title)).parentNode,
        document.createElement("h3").appendChild(document.createTextNode(card.date)).parentNode,
        document.createElement("p").appendChild(document.createTextNode(card.description)).parentNode,
    )

    document.getElementById("cards-container").appendChild(cardElement)
}