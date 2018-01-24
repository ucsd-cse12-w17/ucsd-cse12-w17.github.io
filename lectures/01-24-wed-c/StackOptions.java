    // A 
    this.contents.add(0, t); // push
    return this.contents.remove(this.contents.size() - 1); // pop

    // B  (works for stacks)
    this.contents.add(0, t); // push
    return this.contents.remove(0); // pop

    // C
    this.contents.add(this.contents.size() - 1, t); // push
    return this.contents.remove(0); // pop

    // D (would work removing the first -1)
    this.contents.add(this.contents.size() - 1, t); // push
    return this.contents.remove(this.contents.size() - 1); // pop

