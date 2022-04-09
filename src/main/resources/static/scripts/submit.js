function onCardSubmit() {
    fetch("/api", {
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            title: document.getElementById("title-input").value,
            description: document.getElementById("description-input").value
        })
    }).then(response => response.json()).then(data => {
        alert(`Created new card with id ${data}`)
    })
}