
let notes = {};
const box = document.getElementById("box");

const chunk = (arr, len) => {
  let chunks = [],
      i = 0,
      n = arr.length;

  while (i < n) {
    chunks.push(arr.slice(i, i += len));
  }

  return chunks;
}

fetch("/api/notes").then((res) => res.json()).then((json) => {
    notes = json.reduce((acc, note) => {
        acc[note.spn] = note;
        return acc;
    }, {});
    console.log(json.length)
    const chunked = chunk(json, 17);
    box.innerHTML = chunked.map((octave) =>
        `
        <select multiple size="17">
            ${octave.map((note) => "<option value='" + note.spn + "'>" + note.spn + "</option>")}
        </select>
        `
    )
})

const sendNotes = (event) => {
    const selected = document.querySelectorAll('#box option:checked');
    const values = Array.from(selected).map((opt) => opt.value);
    const fullObjects = values.map((spn) => notes[spn])
    fetch("/api/chord", {
        body: JSON.stringify(fullObjects),
        headers: {
            "Content-Type": "application/json"
        },
        method: "POST"
    })
    .then((res => res.json()))
    .then((json) =>
        document.getElementById("chord").innerText = JSON.stringify(json, null, 2)
    );
};